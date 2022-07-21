define(function() {
    const MapUtils = (function() {
        MapUtils.prototype.find = function(map, path) {
            const pathArray = path.split('\.');

            var current = map;
            for(var idx in pathArray) {
                current = current[pathArray[idx]];
            }
            return current;
        }
        return MapUtils.prototype;
    });
    return new MapUtils();
})