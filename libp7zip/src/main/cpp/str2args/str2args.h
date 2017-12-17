#ifndef ANDROIDP7ZIP_STR2ARGS_H
#define ANDROIDP7ZIP_STR2ARGS_H

#ifdef __cplusplus
extern "C" {
#endif

#define ARGV_LEN_MAX    512
#define ARGC_MAX        256

bool str2args(const char *s, char argv[][ARGV_LEN_MAX], int* argc);

#ifdef __cplusplus
}
#endif

#endif
