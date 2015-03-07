'use strict';

module.exports = function(grunt) {
    require('load-grunt-tasks')(grunt, { scope: 'devDependencies' });

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        distDir: 'src/main/webapp/',
        distDirCss: '<%= distDir %>/css',
        distDirJs: '<%= distDir %>/js',
        distDirImg: '<%= distDir %>/images',
        distDirFonts: '<%= distDir %>/fonts',
        sassSource: 'src/sass',
        bootstrapDir: 'node_modules/bootstrap-sass/assets',
        jqueryDir: 'node_modules/jquery/dist',


        autoprefixerBrowsers: [
            "last 2 versions",
            "Firefox >= 24",
            "Explorer >= 9"
        ],

        // --- Task configuration ---
        clean: {
          build: {
            src: ['<%= distDir %>/css', '<%= distDir %>/js','<%= distDir %>/fonts','<%= distDir %>/images']
          }
        },
        sass: {
              dist: {
                files: {
                    '<%= distDir %>/css/recipe-website.css':'<%= sassSource %>/style.scss',
                    '<%= distDir %>/css/bootstrap.css':'<%= sassSource %>/bootstrap.scss'
                }
              }
            },
        watch: {
            sass: {
                files: '**/*.scss',
                tasks: ['sass']
            }
        },
        copy: {
          fonts: {
            expand: true,
            cwd: '<%=  bootstrapDir %>/fonts/',
            src: '**',
            dest: '<%=  distDirFonts %>'
          },
          javascripts: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/js/*', '<%=  jqueryDir %>/jquery.js', '<%=  bootstrapDir %>/javascripts/bootstrap.js'],
            dest: '<%=  distDirJs %>'
          },
          images: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/images/**'],
            dest: '<%=  distDirImg %>'
          }
        },
        autoprefixer: {
          options: {
            browsers: '<%= autoprefixerBrowsers %>',
            map: true
          },
          recipeWebsite: {
            src: '<%= distDirCss %>/recipe-website.css'
          }
        }

    });
    grunt.loadNpmTasks('grunt-contrib-sass');
    	grunt.loadNpmTasks('grunt-contrib-watch');
// --- Tasks ---
  grunt.registerTask('dist-css', ['sass', 'autoprefixer']);

  // Full distribution task.
  grunt.registerTask('dist', ['clean', 'copy', 'dist-css']);

  // Default task.
  grunt.registerTask('default', ['dist']);

};