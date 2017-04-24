const gulp = require('gulp');
const less = require('gulp-less');
const autoprefixer = require('gulp-autoprefixer');
const LessAutoprefix = require('less-plugin-autoprefix');
const autoprefix = new LessAutoprefix({ browsers: ['last 2 versions'] });
const babel = require('gulp-babel');
const uglify = require('gulp-uglify');

gulp.task('less', () => {
  return gulp
    .src('./less/styles.less')
    .pipe(less({
      plugins: [autoprefix]
    }))
    .pipe(gulp.dest('./resources/schuleinzugsgebiete/public/css'));
});

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
    .src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));
});

gulp.task('watch', () => {
  gulp.watch('less/**/*.less', ['less']);
  gulp.watch('js/**/*.js', ['babel']);
});

gulp.task('default', ['copy', 'less', 'babel']);
