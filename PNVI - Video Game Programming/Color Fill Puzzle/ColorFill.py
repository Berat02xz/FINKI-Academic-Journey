import pygame
import sys

# Initialize PyGame
pygame.init()

# Constants
SCREEN_SIZE = 500
GRID_SIZE = 5
SQUARE_SIZE = SCREEN_SIZE // GRID_SIZE
COLORS = [(255, 0, 0), (0, 255, 0), (0, 0, 255), (255, 255, 0), (255, 0, 255), (0, 255, 255)]  # More colors
BG_COLOR = (255, 255, 255)
TEXT_COLOR = (0, 0, 0)
FONT = pygame.font.Font(None, 36)

# Initialize the screen
screen = pygame.display.set_mode((SCREEN_SIZE, SCREEN_SIZE))
pygame.display.set_caption("Color Fill Puzzle")

# Game state
grid = [[-1 for _ in range(GRID_SIZE)] for _ in range(GRID_SIZE)]  # -1 indicates uncolored
current_color = 0  # Start with the first color

def draw_grid():
    screen.fill(BG_COLOR)
    for row in range(GRID_SIZE):
        for col in range(GRID_SIZE):
            color = BG_COLOR if grid[row][col] == -1 else COLORS[grid[row][col]]
            pygame.draw.rect(screen, color, (col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE))
            pygame.draw.rect(screen, TEXT_COLOR, (col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE), 2)

def is_valid_move(row, col, color):
    neighbors = [
        (row - 1, col), (row + 1, col),  # Top, Bottom
        (row, col - 1), (row, col + 1)   # Left, Right
    ]
    for r, c in neighbors:
        if 0 <= r < GRID_SIZE and 0 <= c < GRID_SIZE and grid[r][c] == color:
            return False
    return True

def check_victory():
    for row in range(GRID_SIZE):
        for col in range(GRID_SIZE):
            if grid[row][col] == -1 or not is_valid_move(row, col, grid[row][col]):
                return False
    return True

def display_message(message):
    text = FONT.render(message, True, TEXT_COLOR)
    text_rect = text.get_rect(center=(SCREEN_SIZE // 2, SCREEN_SIZE // 2))
    screen.blit(text, text_rect)
    pygame.display.flip()

# Main loop
running = True
victory = False

while running:
    draw_grid()
    if victory:
        display_message("Pobedivte!")
    pygame.display.flip()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEBUTTONDOWN and not victory:
            x, y = pygame.mouse.get_pos()
            row, col = y // SQUARE_SIZE, x // SQUARE_SIZE
            if grid[row][col] == -1:  # Only allow coloring uncolored cells
                if is_valid_move(row, col, current_color):
                    grid[row][col] = current_color
                    if check_victory():
                        victory = True
                current_color = (current_color + 1) % len(COLORS)  # Cycle to the next color

pygame.quit()
sys.exit()
