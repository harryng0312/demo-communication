var HCrypto = {
    subtle: window.crypto.subtle ? window.crypto.subtle : widow.msCrypto.subtle,
    // SHA-256
    hash: function (algName, str) {
        var promise = this.subtle.digest({name: algName}, DataUtil.strToBytes(str));
        promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        });
        var rs = promise;
        return rs;
    },
    // HMAC RSA-PSS ECDSA
    sign: function (param, key, str) {
        var keyBin = DataUtil.base64ToBytes(key);
        var promise = this.subtle.sign(param, keyBin, DataUtil.strToBytes(str));
        promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        }).catch(function (err) {
            console.error(err);
        });
        var rs = promise;
        return rs;
    },
    verify: function (algName, key, str) {
        var keyBin = DataUtil.base64ToBytes(key);
        var promise = this.subtle.verify(param, keyBin, DataUtil.strToBytes(str));
        promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        }).catch(function (err) {
            console.error(err);
        });
        var rs = promise;
        return rs;
    },

    //
    generateKey: function (param, keyUsages) {
        var keyPair = this.subtle.generateKey(param, true, keyUsages);
        return keyPair;
    },
}
