define(["ArgsUtils"], function(ArgsUtils) {
    const StringUtil = (function() {
        const V_VAR_REGEX = /\{\}/g;
        const V_VAR_NAME_REGEX = /\{%[a-zA-Z]+%\}/g;
        const V_NOT_START_ALPHABET = /[^a-zA-Z]+/g;
        const V_BLANK = '';

        StringUtil.prototype.format = function(str, args) {
            let extendedArgs = [];
            const extArgs = ArgsUtils.get(arguments, 2);
            if(args instanceof Array) {
                extendedArgs = extendedArgs.concat(args);
            } else {
                extendedArgs.push(args);
            }

            if(extArgs && extArgs.length > 0) {
                extendedArgs = extendedArgs.concat(extArgs);
            }

            const argsLength = extendedArgs.length;
            let result = str;
            const matches = str.match(V_VAR_REGEX);
            for(let idx in matches) {
                let arg = argsLength > idx ? extendedArgs[idx] : V_BLANK;

                if(arg === "undefined") {
                    arg = V_BLANK;
                }
                result = result.replace(matches[idx], arg);
            }
            return result;
        }

        StringUtil.prototype.formatObj = function(str, argObj) {
            let result = str;
            const matches = str.match(V_VAR_NAME_REGEX);
            for(let idx in matches) {
                const matchName = matches[idx].replaceAll(V_NOT_START_ALPHABET, V_BLANK);
                const arg = argObj.hasOwnProperty(matchName) ? argObj[matchName] : V_BLANK;
                result = result.replace(matches[idx], arg);
            }
            return result;
        }

        return StringUtil.prototype;
    });

    return new StringUtil();
})