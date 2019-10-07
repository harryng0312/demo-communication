var GTextEncoder = new TextEncoder('utf-8');
var GTextDecoder = new TextDecoder("utf-8");
var DataUtil = {
    strToBase64: function (str) {
        var strBin = this.strToBytes(str);
        return this.bytesToBase64(strBin);
    },
    base64ToStr: function (b64) {
        var b64Bin = this.base64ToBytes(b64);
        return this.bytesToStr(b64Bin);
    },
    bytesToByteArr: function (buffer) {
        var byteArr = Array.from(new Uint8Array(buffer));
        return byteArr;
    },

    bytesToBase64: function (buffer) {
        var b64 = btoa(String.fromCharCode.apply(null, new Uint8Array(buffer)));
        return b64;
    },
    base64ToBytes: function (b64) {
        return atob(b64);
    },

    strToBytes: function (str) {
        var bytes = GTextEncoder.encode(str);
        return bytes;
    },
    bytesToStr: function (buffer) {
        var str = GTextDecoder.decode(buffer);
        return str;
    },

    bigIntToBytes: function (bn) {
        if (bn != null && bn !== undefined && bn instanceof BigInt) {
            var hex = bn.toString(16);
            if (hex.length % 2) {
                hex = '0' + hex;
            }
            var len = hex.length / 2;
            var u8 = new Uint8Array(len);
            var i = 0;
            var j = 0;
            while (i < len) {
                u8[i] = parseInt(hex.slice(j, j + 2), 16);
                i += 1;
                j += 2;
            }
            return u8;
        }
    },
    bytesToBigInt: function (buf) {
        var hex = [];
        var u8 = Uint8Array.from(buf);
        u8.forEach(function (i) {
            var h = i.toString(16);
            if (h.length % 2) {
                h = '0' + h;
            }
            hex.push(h);
        });
        return BigInt('0x' + hex.join(''));
    }
};
var FormUtil = {
    postJson: function (url, data, success, error) {
        $.ajax({
            url: url,
            crossDomain: false,
            dataType: "json",
            headers: {
                'accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            scriptCharset: "utf-8",
            processData: false,
            data: JSON.stringify(data),
            success: success,
            error: error,
        });
    }
};