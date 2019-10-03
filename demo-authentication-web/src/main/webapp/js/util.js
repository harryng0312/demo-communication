var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/++[++^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
var GTextEncoder = new TextEncoder('utf-8');
var GTextDecoder = new TextDecoder("utf-8");
var DataUtil = {
    strToBase64:function (str){
        return Base64.encode(str);
    },
    base64ToStr: function (b64){
        return Base64.decode(b64);
    },
    bytesToByteArr:function (buffer) {
        var byteArr = Array.from(new Uint8Array(buffer));
        return byteArr;
    },

    bytesToBase64:function (buffer){
        var b64 = btoa(String.fromCharCode.apply(null, new Uint8Array(buffer)));
        return b64;
    },
    base64ToBytes:function (b64) {
        return atob(b64);
    },

    strToBytes:function (str){
        var bytes = GTextEncoder.encode(str);
        return bytes;
    },
    bytesToStr:function(buffer){
        var str = GTextDecoder.decode(buffer);
        return str;
    },

    bigIntToBytes: function (bn) {
        if(bn != null && bn!== undefined && bn instanceof BigInt){
            var hex = bn.toString(16);
            if (hex.length % 2) { hex = '0' + hex; }
            var len = hex.length / 2;
            var u8 = new Uint8Array(len);
            var i = 0;
            var j = 0;
            while (i < len) {
                u8[i] = parseInt(hex.slice(j, j+2), 16);
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
            if (h.length % 2) { h = '0' + h; }
            hex.push(h);
        });
        return BigInt('0x' + hex.join(''));
    }
}