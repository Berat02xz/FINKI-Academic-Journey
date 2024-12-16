import pygame
import random
import sys

# Initialize pygame
pygame.init()

# Screen dimensions
WIDTH, HEIGHT = 800, 600

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

#Materials
spaceship_img = pygame.image.load('spaceship.png')
asteroid_img = pygame.image.load('asteroid.png')
energy_crystal_img = pygame.image.load('energy_crystal.png')
background_music = 'background_music.wav'
clash_sound = pygame.mixer.Sound('clash_sound.wav')

# Scale Materials
spaceship_img = pygame.transform.scale(spaceship_img, (50, 50))
asteroid_img = pygame.transform.scale(asteroid_img, (60, 40))
energy_crystal_img = pygame.transform.scale(energy_crystal_img, (50, 30))

# Setup screen
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Space Scavenger")
clock = pygame.time.Clock()

#Music Play
pygame.mixer.music.load(background_music)
pygame.mixer.music.set_volume(0.5)
pygame.mixer.music.play(-1)

# Spaceship class
class Spaceship(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = spaceship_img
        self.rect = self.image.get_rect(center=(WIDTH // 2, HEIGHT // 2))
        self.speed = 5

    def update(self):
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT] and self.rect.left > 0:
            self.rect.x -= self.speed
        if keys[pygame.K_RIGHT] and self.rect.right < WIDTH:
            self.rect.x += self.speed
        if keys[pygame.K_UP] and self.rect.top > 0:
            self.rect.y -= self.speed
        if keys[pygame.K_DOWN] and self.rect.bottom < HEIGHT:
            self.rect.y += self.speed

# Asteroid class
class Asteroid(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = asteroid_img
        side = random.choice(['left', 'right', 'top', 'bottom'])
        if side == 'left':
            self.rect = self.image.get_rect(center=(random.randint(-50, 0), random.randint(0, HEIGHT)))
        elif side == 'right':
            self.rect = self.image.get_rect(center=(random.randint(WIDTH, WIDTH + 50), random.randint(0, HEIGHT)))
        elif side == 'top':
            self.rect = self.image.get_rect(center=(random.randint(0, WIDTH), random.randint(-50, 0)))
        else:
            self.rect = self.image.get_rect(center=(random.randint(0, WIDTH), random.randint(HEIGHT, HEIGHT + 50)))
        self.speed_x = random.choice([-1, 1]) * random.uniform(0.5, 2)
        self.speed_y = random.choice([-1, 1]) * random.uniform(0.5, 2)

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y

        # Remove asteroid if it moves off screen
        if (self.rect.right < 0 or self.rect.left > WIDTH or
                self.rect.bottom < 0 or self.rect.top > HEIGHT):
            self.kill()

# EnergyCrystal class
class EnergyCrystal(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = energy_crystal_img
        side = random.choice(['left', 'right', 'top', 'bottom'])
        if side == 'left':
            self.rect = self.image.get_rect(center=(random.randint(-50, 0), random.randint(0, HEIGHT)))
        elif side == 'right':
            self.rect = self.image.get_rect(center=(random.randint(WIDTH, WIDTH + 50), random.randint(0, HEIGHT)))
        elif side == 'top':
            self.rect = self.image.get_rect(center=(random.randint(0, WIDTH), random.randint(-50, 0)))
        else:
            self.rect = self.image.get_rect(center=(random.randint(0, WIDTH), random.randint(HEIGHT, HEIGHT + 50)))
        self.speed_x = random.choice([-1, 1]) * random.uniform(0.5, 2)
        self.speed_y = random.choice([-1, 1]) * random.uniform(0.5, 2)

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y
        if (self.rect.right < 0 or self.rect.left > WIDTH or
                self.rect.bottom < 0 or self.rect.top > HEIGHT):
            self.kill()

# Background Grid
class Grid:
    def __init__(self):
        self.offset_x = 0
        self.offset_y = 0
        self.cell_size = 40

    def update(self, dx, dy):
        self.offset_x = (self.offset_x + dx) % self.cell_size
        self.offset_y = (self.offset_y + dy) % self.cell_size

    def draw(self, surface):
        for x in range(-self.cell_size, WIDTH, self.cell_size):
            for y in range(-self.cell_size, HEIGHT, self.cell_size):
                rect = pygame.Rect(x + self.offset_x, y + self.offset_y, self.cell_size, self.cell_size)
                pygame.draw.rect(surface, WHITE, rect, 1)

# Group sprites
all_sprites = pygame.sprite.Group()
asteroids = pygame.sprite.Group()
energy_crystals = pygame.sprite.Group()

#Player Create
player = Spaceship()
all_sprites.add(player)

# Create grid
grid = Grid()

#Main Variables Set
score = 0
font = pygame.font.Font(None, 36)
game_over = False

# Game loop
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

    if not game_over:
        # Spawn asteroids
        if random.randint(1, 30) == 1:
            asteroid = Asteroid()
            all_sprites.add(asteroid)
            asteroids.add(asteroid)

        # Spawn energy crystals
        if random.randint(1, 60) == 1:
            energy_crystal = EnergyCrystal()
            all_sprites.add(energy_crystal)
            energy_crystals.add(energy_crystal)

        # Update
        dx = dy = 0
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT]:
            dx = player.speed
        elif keys[pygame.K_RIGHT]:
            dx = -player.speed
        if keys[pygame.K_UP]:
            dy = player.speed
        elif keys[pygame.K_DOWN]:
            dy = -player.speed

        grid.update(dx, dy)
        all_sprites.update()

        # Move asteroids and crystals with the grid
        for sprite in asteroids.sprites() + energy_crystals.sprites():
            sprite.rect.x += dx
            sprite.rect.y += dy

        # Check collisions
        if pygame.sprite.spritecollideany(player, asteroids):
            clash_sound.play()
            game_over = True

        collected_crystals = pygame.sprite.spritecollide(player, energy_crystals, True)
        score += len(collected_crystals)

    # Draw
    screen.fill(BLACK)
    grid.draw(screen)
    all_sprites.draw(screen)

    # Display score
    score_text = font.render(f"Score: {score}", True, WHITE)
    screen.blit(score_text, (10, 10))

    if game_over:
        game_over_text = font.render("GAME OVER! Press R to Restart", True, WHITE)
        screen.blit(game_over_text, (WIDTH // 2 - game_over_text.get_width() // 2, HEIGHT // 2 - game_over_text.get_height() // 2))

    # Restart game
    keys = pygame.key.get_pressed()
    if game_over and keys[pygame.K_r]:
        all_sprites.empty()
        asteroids.empty()
        energy_crystals.empty()
        player = Spaceship()
        all_sprites.add(player)
        score = 0
        game_over = False

    pygame.display.flip()
    clock.tick(60)
