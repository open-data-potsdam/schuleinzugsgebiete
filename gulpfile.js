const gulp = require('gulp');
const less = require('gulp-less');
const autoprefixer = require('gulp-autoprefixer');
const LessAutoprefix = require('less-plugin-autoprefix');
const autoprefix = new LessAutoprefix({ browsers: ['last 2 versions'] });

gulp.task('less', () => {
  return gulp
    .src('./less/styles.less')
    .pipe(less({
      plugins: [autoprefix]
    }))
    .pipe(gulp.dest('./resources/schuleinzugsgebiete/public/css'));
});

gulp.task('copy', () => {
  gulp
    .src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('resources/schuleinzugsgebiete/public/vendor'));
});
