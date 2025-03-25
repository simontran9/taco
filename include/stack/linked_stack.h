#ifndef LINKEDSTACK_H
#define LINKEDSTACK_H

#include <stdbool.h>
#include <stdlib.h>

#define GENERATE_NODE_STRUCT(name, type) \
    typedef struct Node_##name { \
        type data; \
        struct Node_##name *next; \
    } Node_##name;

#define GENERATE_LINKEDSTACK_STRUCT(name, type) \
    typedef struct LinkedStack_##name { \
        Node_##name *head; \
        size_t len; \
    } LinkedStack_##name;

#define GENERATE_LINKEDSTACK_ERROR_ENUM(name) \
    typedef enum LinkedStackError_##name { \
        EMPTY_STACK_ERROR_##name, \
        MEMORY_ERROR_##name, \
        SUCCESS_##name, \
    } LinkedStackError_##name;

#define GENERATE_LINKEDSTACK_CREATE(name) \
    LinkedStack_##name *linkedstack_create_##name() { \
        LinkedStack_##name *stack = malloc(sizeof(LinkedStack_##name)); \
        if (stack == NULL) { return NULL; } \
        stack->head = NULL; \
        stack->len = 0; \
        return stack; \
    }

#define GENERATE_LINKEDSTACK_DESTROY(name) \
    LinkedStackError_##name linkedstack_destroy_##name(LinkedStack_##name *stack) { \
        Node_##name *current = stack->head; \
        while (current != NULL) { \
            Node_##name *temp = current; \
            current = current->next; \
            free(temp); \
        } \
        free(stack); \
        return SUCCESS_##name; \
    }

#define GENERATE_LINKEDSTACK_LEN(name) \
    size_t linkedstack_len_##name(LinkedStack_##name *stack) { return stack->len; }

#define GENERATE_LINKEDSTACK_IS_EMPTY(name) \
    bool linkedstack_is_empty_##name(LinkedStack_##name *stack) { return stack->len == 0; }

#define GENERATE_LINKEDSTACK_TOP(name, type) \
    LinkedStackError_##name linkedstack_top_##name(LinkedStack_##name *stack, type *out_element) { \
        if (stack->len == 0) { return EMPTY_STACK_ERROR_##name; } \
        *out_element = stack->head->data; \
        return SUCCESS_##name; \
    }

#define GENERATE_LINKEDSTACK_PUSH(name, type) \
    LinkedStackError_##name linkedstack_push_##name(LinkedStack_##name *stack, type element) { \
        Node_##name *node = malloc(sizeof(Node_##name)); \
        if (node == NULL) { return MEMORY_ERROR_##name; } \
        node->data = element; \
        node->next = stack->head; \
        stack->head = node; \
        stack->len += 1; \
        return SUCCESS_##name; \
    }

#define GENERATE_LINKEDSTACK_POP(name, type) \
    LinkedStackError_##name linkedstack_pop_##name(LinkedStack_##name *stack, type *out_element) { \
        if (stack->len == 0) { return EMPTY_STACK_ERROR_##name; } \
        *out_element = stack->head->data; \
        Node_##name *temp = stack->head; \
        stack->head = stack->head->next; \
        temp->next = NULL; \
        free(temp); \
        stack->len -= 1; \
        return SUCCESS_##name; \
    }

/*
 * User-facing macro which generates using helper-macros
 * all the struct definitions and functions.
 */
#define GENERATE_LINKEDSTACK(name, type) \
    GENERATE_NODE_STRUCT(name, type) \
    GENERATE_LINKEDSTACK_STRUCT(name, type) \
    GENERATE_LINKEDSTACK_ERROR_ENUM(name) \
    GENERATE_LINKEDSTACK_CREATE(name) \
    GENERATE_LINKEDSTACK_DESTROY(name) \
    GENERATE_LINKEDSTACK_LEN(name) \
    GENERATE_LINKEDSTACK_IS_EMPTY(name) \
    GENERATE_LINKEDSTACK_TOP(name, type) \
    GENERATE_LINKEDSTACK_PUSH(name, type) \
    GENERATE_LINKEDSTACK_POP(name, type)

/*
 * User-facing macros which will call functions generated
 * by the generate macro (and its helper macros).
 */
#define LINKEDSTACK_CREATE(name) linkedstack_create_##name()
#define LINKEDSTACK_DESTROY(name, stack) linkedstack_destroy_##name(stack)
#define LINKEDSTACK_LEN(name, stack) linkedstack_len_##name(stack)
#define LINKEDSTACK_IS_EMPTY(name, stack) linkedstack_is_empty_##name(stack)
#define LINKEDSTACK_TOP(name, stack, out_element) linkedstack_top_##name(stack, out_element)
#define LINKEDSTACK_PUSH(name, stack, element) linkedstack_push_##name(stack, element)
#define LINKEDSTACK_POP(name, stack, out_element) linkedstack_pop_##name(stack, out_element)

#endif /* LINKEDSTACK_H */
