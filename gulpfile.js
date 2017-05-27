const gulp = require('gulp');
const babel = require('gulp-babel');
const uglify = require('gulp-uglify');

gulp.task('babel', () => {
  return gulp.src('js/app.js')
    .pipe(babel({
      presets: ['es2015']
    }))
    .pipe(uglify())
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/js'));
});

gulp.task('copy', () => {
  gulp
    .src('node_modules/tachyons/css/tachyons.min.css')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));

  gulp
    .src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));

  gulp
    .src('node_modules/leaflet/dist/leaflet.js')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));

  gulp
    .src('node_modules/leaflet/dist/leaflet.css')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));
});

gulp.task('watch', () => {
  gulp.watch('js/**/*.js', ['babel']);
});

gulp.task('default', ['copy', 'babel']);
