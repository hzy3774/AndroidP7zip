// NewHandler.cpp
 
#include "StdAfx.h"

#include "../../C/Alloc.h"



/* An overload function for the C++ new */
void * operator new(size_t size)
{
  return MyAlloc(size);
}

/* An overload function for the C++ new[] */
void * operator new[](size_t size)
{
    return MyAlloc(size);
}

/* An overload function for the C++ delete */
void operator delete(void *pnt)
{
    MyFree(pnt);
}

/* An overload function for the C++ delete[] */
void operator delete[](void *pnt)
{
    MyFree(pnt);
}

