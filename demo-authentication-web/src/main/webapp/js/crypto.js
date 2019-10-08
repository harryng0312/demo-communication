const HCrypto = {
    subtle: window.crypto.subtle ? window.crypto.subtle : widow.msCrypto.subtle,
    // SHA-256
    hash: function (algName, str) {
        const promise = this.subtle.digest({name: algName}, DataUtil.strToBytes(str));
        promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        });
        return promise;
    },
    // HMAC RSA-PSS ECDSA
    sign: function (param, key, str) {
        const keyBin = DataUtil.base64ToBytes(key);
        const promise = this.subtle.sign(param, keyBin, DataUtil.strToBytes(str));
        return promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        }).catch(function (err) {
            console.error(err);
        });
    },
    verify: function (algName, key, str) {
        const keyBin = DataUtil.base64ToBytes(key);
        const promise = this.subtle.verify(param, keyBin, DataUtil.strToBytes(str));
        return promise.then(function (value) {
            return DataUtil.bytesToBase64(value);
        }).catch(function (err) {
            console.error(err);
        });
    },

    //
    generateKey: function (param, keyUsages) {
        const keyPair = this.subtle.generateKey(param, true, keyUsages);
        return keyPair;
    },
};
