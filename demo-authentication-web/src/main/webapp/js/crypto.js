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
    mac: function (algName, key, str) {

    }
}
