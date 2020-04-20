var Authenticator = {
    loginByUnamePasswd: function (uname, passwd, callback) {
        var data = {
            username: uname,
            password: passwd
        };
        var success = function (data) {
            var result = (data.result === "0");
            alert("Login result:" + result);
            callback(result);
        };
        var error = function (err) {
            alert(err);
            console.log("Error:" + err);
            callback(false);
        };
        FormUtil.postJson("login", data, success, error);
    },
    loginByECDH: function (uname, passwd, callback) {
        var promise = new Promise(function (resolve, reject) {
            resolve(passwd)
        });
        promise.then(async function (val) {
            var data = DataUtil.strToBytes(val);
            const hashPasswd = await HCrypto.hash("SHA-256", data);
            console.log("Hashed passwd:" + hashPasswd);
            var sqrHashedPwd = DataUtil.bytesToBigInt(DataUtil.base64ToBytes(hashPasswd)).pow(2);
            console.log("Num passwd:" + sqrHashedPwd);
            const dhParams = {
                name: "ECDH",
                namedCurve: "P-256"
            };
            const keyPair = await HCrypto.generateKey(dhParams, ["deriveKey", "deriveBits"]);
            var priKey = keyPair.privateKey;
            var pubKey = keyPair.publicKey;
            var priKeyData = await HCrypto.exportKey("jwk", priKey);
            var pubKeyData = await HCrypto.exportKey("jwk", pubKey);
            console.log("Pri Key:" + JSON.stringify(priKeyData));
            console.log("Pub Key:" + JSON.stringify(pubKeyData));
            var commonSecretKey = await HCrypto.deriveKey({
                    name: "ECDH",
                    namedCurve: "P-256",
                    public: pubKey
                },
                priKey,
                {
                    name: "AES-CTR",
                    length: 256
                }, ["encrypt", "decrypt"]);
            var keyData = await HCrypto.exportKey("jwk", commonSecretKey);
            console.log("Common Key:" + JSON.stringify(keyData));
        }).catch(function (err) {
            alert(err);
        });
    },
    loginByPBKDF2: function (uname, passwd, callback) {
        var salt = DataUtil.strToBytes("0000");
        var iterations = 10240;
        var promise = new Promise(function (resolve, reject) {
            resolve(passwd)
        });
        promise.then(async function (val) {
            var passwdBin = DataUtil.strToBytes(val);
            var key = await HCrypto.importKey("raw",
                passwdBin,
                {name: "PBKDF2"},
                ['deriveKey', 'deriveBits']);
            var webKey = await HCrypto.deriveKey({
                    name: "PBKDF2",
                    salt: salt,
                    iterations: iterations,
                    hash: "SHA-256"
                }, key,
                {name: "AES-CBC", length: 128},
                ["encrypt", "decrypt"]);
            var sKey = await HCrypto.exportKey("raw", webKey);
            console.log("Secret key:" + DataUtil.bytesToBase64(sKey));
            var counter = new Uint8Array(16);
            counter.fill(0);
            counter[counter.byteLength-1] = 1;
            var pDataBin = DataUtil.strToBytes("abcdefghijklmnopqrstuvwxyz0123456789");
            var cDataBin = await HCrypto.encrypt({
                name:"AES-CBC",
                counter: counter,
                length:128,
                iv: counter
            }, webKey, pDataBin);
            console.log("CData:" + DataUtil.bytesToBase64(cDataBin));
        }).catch(function (err) {
            console.log(err);
        });
    }
}