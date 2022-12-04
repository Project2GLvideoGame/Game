package engine.input;

import game.Game;

/**
 * Schéma classique devant être utilisé lorsqu'on souhaitera créer des états concrets côté Gameplay.
 * Si une touche ne sera pas utilisée, on devra laisser sa méthode vide.
 */
public interface State {

    /**
     * La touche flèche du haut est pressée
     * @param game L'instance du jeu
     */
    void upPressed(Game game);
    /**
     * La touche flèche du haut est relâchée
     * @param game L'instance du jeu
     */
    void upReleased(Game game);
    /**
     * La touche flèche du bas est pressée
     * @param game L'instance du jeu
     */
    void downPressed(Game game);
    /**
     * La touche flèche du bas est relâchée
     * @param game L'instance du jeu
     */
    void downReleased(Game game);
    /**
     * La touche flèche de droite est pressée
     * @param game L'instance du jeu
     */
    void rightPressed(Game game);
    /**
     * La touche flèche de droite est relâchée
     * @param game L'instance du jeu
     */
    void rightReleased(Game game);
    /**
     * La touche flèche de gauche est pressée
     * @param game L'instance du jeu
     */
    void leftPressed(Game game);
    /**
     * La touche flèche de gauche est relâchée
     * @param game L'instance du jeu
     */
    void leftReleased(Game game);
    /**
     * La touche espace est pressée
     * @param game L'instance du jeu
     */
    void escapePressed(Game game);
    /**
     * La touche espace est relâchée
     * @param game L'instance du jeu
     */
    void escapeReleased(Game game);
    /**
     * La touche P est pressée
     * @param game L'instance du jeu
     */
    void pPressed(Game game);
    /**
     * La touche P est relâchée
     * @param game L'instance du jeu
     */
    void pReleased(Game game);

}
