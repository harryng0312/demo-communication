var HCrypto = {
    subtle: window.crypto.subtle?window.crypto.subtle:widow.msCrypto.subtle,
    // SHA-256
    hash: async function (algName, str) {
        var promise = this.subtle.digest({name: algName}, strToArrBuffer(str))
            .then(function (value) {
                return DataUtil.bytesToBase64(value);
            })
        var rs = await promise;
        return rs;
    },
    // HMAC RSA-PSS ECDSA
    sign: async function (algName, key, str) {
        var keyBin = DataUtil.base64ToBytes(key);
        var promise = this.subtle.sign({name: algName}, keyBin, DataUtil.strToBytes(str))
            .then(function (value) {
                return DataUtil.bytesToBase64(value);
            }).catch(function(err) {
                console.error(err);
            })
        var rs = await promise;
        return rs;
    },
    verify: async function(algName, key, str){
        var keyBin = DataUtil.base64ToBytes(key);
        var promise = this.subtle.verify({name: algName}, keyBin, DataUtil.strToBytes(str))
            .then(function (value) {
                return DataUtil.bytesToBase64(value);
            }).catch(function(err) {
                console.error(err);
            })
        var rs = await promise;
        return rs;
    },

    //
    generateKey: async function(algName, ){
        this.subtle
    },
}
