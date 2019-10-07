var Authenticator = {
    loginByUnamePasswd: function (uname, passwd, callback) {
        var data = {
            username: uname,
            password: passwd
        };
        var success = function (data) {
            var result = (data.result === "0");
            callback(result);
        };
        var error = function (err) {
            alert(err);
            console.log("Error:" + err);
            callback(false);
        };
        FormUtil.postJson("doLogin", data, success, error);
    }
}