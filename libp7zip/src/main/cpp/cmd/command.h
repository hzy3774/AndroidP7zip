#ifndef ANDROIDP7ZIP_COMMAND_H
#define ANDROIDP7ZIP_COMMAND_H

#ifdef __cplusplus
extern "C" {
#endif

#include <MyTypes.h>

int MY_CDECL
main(
#ifndef _WIN32
        int numArgs, char *args[]
#endif
);

int executeCommand(const char *cmd);

#ifdef __cplusplus
}
#endif

#endif
