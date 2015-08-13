'use strict';

module.exports = function(grunt) {
    require('load-grunt-tasks')(grunt, { scope: 'devDependencies' });

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        webappSourceDir: 'target/recipe-website',
        webappSourceDirCss: '<%= webappSourceDir %>/css',
        webappSourceDirJs: '<%= webappSourceDir %>/js',
        webappSourceDirImg: '<%= webappSourceDir %>/images',
        webappSourceDirFonts: '<%= webappSourceDir %>/fonts',
        webappSourceDirResources: '<%= webappSourceDir %>/resources',
        webappSourceDirJsp: '<%= webappSourceDir %>/WEB-INF/view',
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
            src: ['<%= webappSourceDirCss %>', '<%= webappSourceDirJs %>','<%= webappSourceDirFonts %>','<%= webappSourceDirImg %>','<%= webappSourceDirResources %>']
          }
        },
        sass: {
              dist: {
                files: {
                    '<%= webappSourceDir %>/css/recipe-website.css':'<%= sassSource %>/style.scss',
                    '<%= webappSourceDir %>/css/recipe-website-navbar.css':'<%= sassSource %>/navbar.scss',
                    '<%= webappSourceDir %>/css/bootstrap.css':'<%= sassSource %>/bootstrap.scss'
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
            dest: '<%=  webappSourceDirFonts %>'
          },
          javascripts: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/js/*', '<%=  jqueryDir %>/jquery.js', '<%=  bootstrapDir %>/javascripts/bootstrap.js'],
            dest: '<%=  webappSourceDirJs %>'
          },
          images: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/images/**'],
            dest: '<%=  webappSourceDirImg %>'
          },
          resources: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/main/webapp/WEB-INF/resources/**'],
            dest: '<%=  webappSourceDirResources %>'
          },
          jsp: {
            expand: true,
            flatten: true,
            filter: 'isFile',
            src: ['src/main/webapp/WEB-INF/view/**'],
            dest: '<%=  webappSourceDirJsp %>'
          }
        },
        autoprefixer: {
          options: {
            browsers: '<%= autoprefixerBrowsers %>',
            map: true
          },
          recipeWebsite: {
            src: '<%= webappSourceDirCss %>/recipe-website.css'
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