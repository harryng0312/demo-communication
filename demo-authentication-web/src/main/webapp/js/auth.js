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
            const primeBigInt = bigInt("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A087"
                + "98E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7"
                + "EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655"
                + "D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E"
                + "86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD3"
                + "3170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A"
                + "25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E20"
                + "8E24FA074E5AB3143DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF", 16);
            var data = DataUtil.strToBytes(val);
            const hashPasswd = await HCrypto.hash("SHA-256", data);
            console.log("Hashed passwd:" + hashPasswd);
            var sqrHashedPwd = (DataUtil.bytesToBigInt(DataUtil.base64ToBytes(hashPasswd))**2n);
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
    loginBySPAEKE: function (uname, passwd, callback) {
        const primeBigInt = bigInt("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A087"
            + "98E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7"
            + "EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655"
            + "D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E"
            + "86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD3"
            + "3170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A"
            + "25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E20"
            + "8E24FA074E5AB3143DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF", 16);
        const prime = primeBigInt; //DataUtil.bigIntToBytes(primeBigInt);
        var promise = new Promise(function (resolve, reject) {
            resolve(passwd)
        });
        promise.then(async function (val) {
            var data = DataUtil.strToBytes(val);
            const hashPasswd = await HCrypto.hash("SHA-256", data);
            console.log("Hashed passwd:" + hashPasswd);
            var sqrHashedPwd = BigInt(DataUtil.bytesToBigInt(DataUtil.base64ToBytes(hashPasswd)));
            console.log("Num passwd:" + sqrHashedPwd);
            sqrHashedPwd = sqrHashedPwd ** BigInt(2);
            console.log("Sqr Num passwd:" + sqrHashedPwd);
            const g = sqrHashedPwd;//DataUtil.bigIntToBytes(sqrHashedPwd);
            const dhParams = {
                name: 'DH',
                prime: prime,
                generator: g,
            };
            console.log("G:" + g);
            const keyPair = await HCrypto.generateKey(dhParams, ["deriveKey", "deriveBits"]);
            console.log("Key pair:" + keyPair);
        }).catch(function (err) {
            alert(err);
        });
        console.log(prime);
    },
}