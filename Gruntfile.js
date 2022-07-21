global.__base = __dirname + '/';

module.exports = function(grunt) {
    const srcPath = "src/main/resources/static";
    const destPath = "dist";

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        clean: [destPath],
        requirejs: {
            compile: {
                options: {
                    baseUrl: srcPath + '/js',
                    mainConfigFile: srcPath + "/js/require.js",
                    optimize: "none",
                    name: "../../../../../node_modules/almond/almond",
                    include: "app",
                    // findNestedDependencies: true,
                    // skipModuleInsertion: true,
                    // wrapShim: true,
                    out: destPath + '/js/app.js',
                    wrap: true,
                    onModuleBundleComplete: function (data) {
                        const fs = require('fs'),
                            amdclean = require('amdclean'),
                            outputFile = data.path;

                        fs.writeFileSync(outputFile, amdclean.clean({
                            'filePath': outputFile
                        }));
                    },
                }
            }
        },
        uglify: {
            options: {
                banner: '/*! v<%= pkg.version %> */\n',
                mangle: {
                    toplevel: true
                },
                compress: {
                    drop_console: true
                },
                sourceMap: true,
                output: {
                    ascii_only: true,
                }
            },
            build: {
                src: destPath + "/js/app.js",
                dest: destPath + "/js/app.js"
            }
        },
        copy: {
            index: {
                src: srcPath + "/index.html",
                dest: destPath + "/index.html",
                options: {
                    process: function(content, srcpath) {
                        return content
                            .replace(/<script.*src=\"js\/require\.js\".*<\/script>\n?/m, "") // remove require config js
                            .replace(/<script.*data-main=\"app\".*<\/script>\n?/m, "") // remove requirejs
                            .replace(/<\/head>/,"<script type=\"text/javascript\" src=\"js/app.js\"></script>\n$&") // add minified js
                            ;
                    }
                }
            },
            css: {
                expand: true,
                cwd: srcPath + "/css/",
                src: ["**"],
                dest: destPath + "/css/",
                filter: "isFile",
                options: {
                    process: function(content, srcpath) {
                        if(srcpath.indexOf("requirejs") < 0) {
                            return content
                        }
                    }
                }
            },
            js: {
                expand: true,
                cwd: srcPath + "/js/",
                src: ["plugins/**", "data/**", "!plugins/requirejs*", "!data/message/default.json"],
                dest: destPath + "/js/",
                options: {
                    process: function(content, srcpath) {
                        if(srcpath.indexOf("requirejs") < 0) {
                            return content
                        }
                    }
                }
            },
            plugins: {
                expand: true,
                cwd: srcPath,
                src: "plugins/**",
                dest: destPath,
            },
        },
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-requirejs');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');

    // Default task(s).
    grunt.registerTask('default', ['clean', 'requirejs', 'uglify', 'copy']);
    grunt.registerTask('build', ['clean', 'requirejs', 'uglify', 'copy']);

};