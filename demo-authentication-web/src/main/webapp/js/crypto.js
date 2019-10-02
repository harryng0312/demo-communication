var HCrypto = {
    subtle: window.crypto.subtle,
    // SHA-256
    hash: async function (algName, str) {
        var promise = this.subtle.digest({name: algName}, strToArrBuffer(str))
            .then(function (value) {
                return arrBufferToBase64(value);
            })
        var rs = await promise;
        return rs;
    },
    // HMAC RSA-PSS ECDSA
    sign: async function (algName, key, str) {
        var keyBin = base64ToArrBuffer(key);
        var promise = this.subtle.sign({name: algName}, keyBin, strToArrBuffer(str))
            .then(function (value) {
                return arrBufferToBase64(value);
            }).catch(function(err) {
                console.error(err);
            })
        var rs = await promise;
        return rs;
    },
    verify: async function(algName, key, str){
        var keyBin = base64ToArrBuffer(key);
        var promise = this.subtle.verify({name: algName}, keyBin, strToArrBuffer(str))
            .then(function (value) {
                return arrBufferToBase64(value);
            }).catch(function(err) {
                console.error(err);
            })
        var rs = await promise;
        return rs;
    }
}
