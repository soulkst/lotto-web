define(function() {
    const ArgsUtils = (function() {
        ArgsUtils.prototype.get = function(args, from, to) {
            var start = 0;
            var end = args.length;
            if(from && from < args.length) {
                start = from;
            }
            if(to && to < args.length) {
                end = to;
            }
            return Array.from(args).slice(start, end);
        }
        ArgsUtils.prototype.ifSingle = function(args, from, to) {
            const sliced = this.get(args, from, to);
            if(sliced.length === 1) {
                return sliced[0];
            }
            return sliced;
        }
        return ArgsUtils.prototype;
    })
    return new ArgsUtils();
})