const gulp = require('gulp');
const bower = require('gulp-bower');
const sass = require('gulp-sass');
const sourcemaps = require('gulp-sourcemaps');
const autoprefixer = require('gulp-autoprefixer');

gulp.task('bower', function() {
  return bower();
});

const sassOptions = {
  outputStyle: 'compressed',
  includePaths: [
    './bower_components/bootstrap-sass-official/assets/stylesheets'
  ]
}

const autoprefixerOptions = {
  browsers: ['last 2 versions', '> 5%', 'Firefox ESR']
};

gulp.task('sass', ['bower'], () => {
  return gulp
    .src('sass/styles.scss')
    .pipe(sourcemaps.init())
    .pipe(sass(sassOptions).on('error', sass.logError))
    .pipe(sourcemaps.write())
    .pipe(autoprefixer(autoprefixerOptions))
    .pipe(gulp.dest('resources/public/css'));
});

gulp.task('default', ['bower', 'sass']);
