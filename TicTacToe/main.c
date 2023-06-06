#include <stdio.h>
#include <stdlib.h>

void DrawGrid();

void ChangeGrid();

int PlayerSelection();

void MainGame(int player);


int main() {

    int player = PlayerSelection();

    return EXIT_SUCCESS;
}

void DrawGrid() {
    char grid[29] = {' ', '|', ' ', '|', ' ', '\n', '-', '-', '-',
                     '-', '-', '\n', ' ', '|', ' ', '|', ' ',
                     '\n', '-', '-', '-', '-', '-',
                     '\n', ' ', '|', ' ', '|', ' '};
    for (int i = 0; i < 28; ++i) {
        printf("%c", grid[i]);
    }
}

int PlayerSelection() {
    char playstone;
    int y;
    do {
        printf("Geben Sie den gewuenschten Spielstein an: ");
        scanf(" %c", &playstone);

        if (playstone == 'x') {
            y = 1;
        } else if (playstone == 'o') {
            y = 2;
        } else {
            printf("Error!\nInvalid Playingstone\n");
            y = 0;
        }
    } while (y == 0);
    return y;

}

void ChangeGrid() {

}

void MainGame(int player) {
    if (player == 1) {
        printf("Geben Sie den gewuenschten Zug ein (X/Y) Koordinaten");
    }
}
