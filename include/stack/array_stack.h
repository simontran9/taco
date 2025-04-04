#ifndef ARRAYSTACK_H
#define ARRAYSTACK_H

#include <stdbool.h>
#include <stdlib.h>

/*
 * `INITIAL_CAP` represents the initial expected capacity of the pointer.
 */
#define INITIAL_CAP 10
/*
 * `GROWTH_FACTOR` is the constant by which we expand the backing contiguous
 * area of memory dedicated to the ArrayList.
 */
#define GROWTH_FACTOR 1.5

/*
 * Definition of a stack backed by a heap-allocated dynamic array:
 * - `data` is a pointer to the contiguous area of memory
 * - `len` is the actual number of elements present in the array
 * - `cap` is the current expected maximum amount of elements that may be
 * stored at once
 */
#define GENERATE_ARRAYSTACK_STRUCT(name, type)                                 \
    typedef struct ArrayStack_##name {                                         \
        type *data;                                                            \
        size_t len;                                                            \
        size_t cap;                                                            \
    } ArrayStack_##name;

/*
 * Array errors.
 */
#define GENERATE_ARRAYSTACK_ERROR_ENUM(name)                                   \
    typedef enum ArrayStackError_##name{                                       \
        SUCCESS_##name,                                                        \
        EMPTY_STACK_ERROR_##name,                                              \
        MEMORY_ERROR_##name,                                                   \
    } ArrayStackError_##name;

/*
 * Creates and returns a pointer to a stack.
 * On memory allocation failure, return `NULL`.
 */
#define GENERATE_ARRAYSTACK_CREATE(name, type)                                 \
    ArrayStack_##name *arraystack_create_##name() {                            \
        ArrayStack_##name *stack = malloc(sizeof(ArrayStack_##name));          \
        if (stack == NULL) { return NULL; }                                    \
        stack->data = malloc(sizeof(type) * INITIAL_CAP);                      \
        if (stack->data == NULL) {                                             \
            free(stack);                                                       \
            return NULL;                                                       \
        }                                                                      \
        stack->len = 0;                                                        \
        stack->cap = INITIAL_CAP;                                              \
        return stack;                                                          \
    }

/*
 * Frees `stack`.
 */
#define GENERATE_ARRAYSTACK_DESTROY(name)                                      \
    void arraystack_destroy_##name(ArrayStack_##name *stack) {                 \
        free(stack->data);                                                     \
        free(stack);                                                           \
    }

/*
 * Returns the amount of elements in `stack`.
 */
#define GENERATE_ARRAYSTACK_LEN(name)                                          \
    size_t arraystack_len_##name(ArrayStack_##name *stack) {                   \
        return stack->len;                                                     \
    }

/*
 * Returns whether `stack` is empty or not.
 */
#define GENERATE_ARRAYSTACK_IS_EMPTY(name)                                     \
    bool arraystack_is_empty_##name(ArrayStack_##name *stack) {                \
        return stack->len == 0;                                                \
    }

/*
 * Returns the element at the top of `stack`.
 */
#define GENERATE_ARRAYSTACK_TOP(name, type)                                    \
    ArrayStackError_##name arraystack_top_##name(ArrayStack_##name *stack,     \
                                                 type *out_element) {          \
        if (stack->len == 0) { return EMPTY_STACK_ERROR_##name; }              \
        *out_element = stack->data[stack->len - 1];                            \
        return SUCCESS_##name;                                                 \
    }

/*
 * Helper function to resize the underlying dynamic array.
 */
#define GENERATE_ARRAYSTACK_RESIZE(name, type)                                 \
    ArrayStackError_##name arraystack_resize_##name(ArrayStack_##name *stack,  \
                                                    size_t new_cap) {          \
        /* why `realloc()` over `malloc() + memcpy()`? */                      \
        /* If there's sufficient unallocated memory following the current      \
         * allocation, */                                                      \
        /* `realloc()` can simply extend the existing block without any        \
         * copying at all */                                                   \
        type *new_array = realloc(stack->data, sizeof(type) * new_cap);        \
        if (new_array == NULL) { return MEMORY_ERROR_##name; }                 \
        stack->data = new_array;                                               \
        stack->cap = new_cap;                                                  \
        return SUCCESS_##name;                                                 \
    }

/*
 * Adds an element to the top of `stack`.
 */
#define GENERATE_ARRAYSTACK_PUSH(name, type)                                   \
    ArrayStackError_##name arraystack_push_##name(ArrayStack_##name *stack,    \
                                                  type element) {              \
        if (stack->len == stack->cap) {                                        \
            ArrayStackError_##name resize_result = arraystack_resize_##name(   \
                stack, (size_t) (stack->cap * GROWTH_FACTOR));                 \
            if (resize_result != SUCCESS_##name) { return resize_result; }     \
        }                                                                      \
        stack->data[stack->len] = element;                                     \
        stack->len += 1;                                                       \
        return SUCCESS_##name;                                                 \
    }

/*
 * Removes an element from the top of `stack`.
 */
#define GENERATE_ARRAYSTACK_POP(name, type)                                    \
    ArrayStackError_##name arraystack_pop_##name(ArrayStack_##name *stack,     \
                                                 type *out_element) {          \
        if (stack->len == 0) { return EMPTY_STACK_ERROR_##name; }              \
        *out_element = stack->data[stack->len - 1];                            \
        /* We're not zeroing out the unused element via                        \
         * `stack->data[stack->len - 1] = 0;` */                               \
        /* because doing so has no functional benefit, wastes cpu cycles, and  \
         * may evict useful */                                                 \
        /* data from the cache during the write. */                            \
        stack->len -= 1;                                                       \
        return SUCCESS_##name;                                                 \
    }

/*
 * User-facing macro which generates using helper-macros
 * all the struct definitions and functions.
 */
#define GENERATE_ARRAYSTACK(name, type)                                        \
    GENERATE_ARRAYSTACK_STRUCT(name, type)                                     \
    GENERATE_ARRAYSTACK_ERROR_ENUM(name)                                       \
    GENERATE_ARRAYSTACK_CREATE(name, type)                                     \
    GENERATE_ARRAYSTACK_DESTROY(name)                                          \
    GENERATE_ARRAYSTACK_LEN(name)                                              \
    GENERATE_ARRAYSTACK_IS_EMPTY(name)                                         \
    GENERATE_ARRAYSTACK_TOP(name, type)                                        \
    GENERATE_ARRAYSTACK_RESIZE(name, type)                                     \
    GENERATE_ARRAYSTACK_PUSH(name, type)                                       \
    GENERATE_ARRAYSTACK_POP(name, type)

/*
 * User-facing macros which will call functions generated
 * by the generate macro (and its helper macros).
 */
#define ARRAYSTACK_CREATE(name) arraystack_create_##name()
#define ARRAYSTACK_DESTROY(name, stack) arraystack_destroy_##name(stack)
#define ARRAYSTACK_LEN(name, stack) arraystack_len_##name(stack)
#define ARRAYSTACK_IS_EMPTY(name, stack) arraystack_is_empty_##name(stack)
#define ARRAYSTACK_TOP(name, stack, out_element)                               \
    arraystack_top_##name(stack, out_element)
#define ARRAYSTACK_PUSH(name, stack, element)                                  \
    arraystack_push_##name(stack, element)
#define ARRAYSTACK_POP(name, stack, out_element)                               \
    arraystack_pop_##name(stack, out_element)

#endif /* ARRAYSTACK_H */