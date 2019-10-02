var HCrypto = {
    subtle: window.crypto.subtle,
    // SHA-256
    hash: async function (algName, str) {
        var promise = this.subtle.digest({name: algName}, strToArrBuffer(str))
            .then(function (value) {
                return bufferToHex(value);
            })
        return await promise;
    },
    mac: function (algName, key, str) {

    }
}
