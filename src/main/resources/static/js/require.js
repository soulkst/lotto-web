var require = {
    baseUrl: "js",

    i18n: {
        locale: 'en-us',
    },

    // skipDataMain: true
    paths: {
        "json": "plugins/json",
        "text": "plugins/text",

        "Constants": "common/constants",

        "ArgsUtils": "utils/args_utils",
        "StringUtils": "utils/string_utils",
        "MapUtils": "utils/map_utils",
        "Notification": "utils/notification"

    },
    shim: {
        "json": {
            deps: ["text"]
        },
    }
};