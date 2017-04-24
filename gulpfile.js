const gulp = require('gulp');
const sourcemaps = require('gulp-sourcemaps');
const autoprefixer = require('gulp-autoprefixer');

const autoprefixerOptions = {
  browsers: ['last 2 versions', '> 5%', 'Firefox ESR']
};

gulp.task('copy', () => {
  gulp
    .src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));
});
