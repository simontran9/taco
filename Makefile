SRC_DIR = src
BUILD_DIR = build
BIN_DIR = bin
I_DIR = include

CC = gcc
DEBUG_FLAGS = -O0 -g -fsanitize=address,undefined
RELEASE_FLAGS = -O3 -Werror
COMMON_FLAGS = -I$(I_DIR) -Wall -Wextra -std=c99 -pedantic

BUILD_MODE ?= debug # Default build mode (if none specified)
ifeq ($(BUILD_MODE), debug)
    CFLAGS = $(DEBUG_FLAGS) $(COMMON_FLAGS)
else ifeq ($(BUILD_MODE), release)
    CFLAGS = $(RELEASE_FLAGS) $(COMMON_FLAGS)
else
    $(error Invalid BUILD_MODE specified. Use 'debug' or 'release')
endif

SRC = $(wildcard $(SRC_DIR)/*.c)
OBJ = $(patsubst $(SRC_DIR)/%.c, $(BUILD_DIR)/%.o, $(SRC))
BIN = $(BIN_DIR)/my_program

.PHONY: all debug release thread_debug clean install

# Debug build
debug: BUILD_MODE = debug
debug: all

# Release build
release: BUILD_MODE = release
release: all

# Default target
all: $(BIN)

$(BIN): $(OBJ) | $(BIN_DIR)
	$(CC) $(CFLAGS) $^ -o $@

$(BUILD_DIR)/%.o: $(SRC_DIR)/%.c | $(BUILD_DIR)
	$(CC) $(CFLAGS) -MMD -MP -c $< -o $@

-include $(OBJ:.o=.d)

$(BUILD_DIR) $(BIN_DIR):
	mkdir -p $@

format:
	clang-format -i src/*/*.h

# bench

clean:
	rm -rf $(BUILD_DIR) $(BIN_DIR)