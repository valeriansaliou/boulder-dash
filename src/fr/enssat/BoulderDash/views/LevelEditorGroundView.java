package fr.enssat.BoulderDash.views;

import fr.enssat.BoulderDash.views.GroundView;
import fr.enssat.BoulderDash.views.LevelEditorView;
import fr.enssat.BoulderDash.controllers.LevelEditorKeyController;
import fr.enssat.BoulderDash.models.LevelModel;


/**
 * LevelEditorFieldView
 *
 * Game field view for the level editor.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-21
 */
public class LevelEditorGroundView extends GroundView {
    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorGroundView(LevelModel levelModel, LevelEditorView levelEditorView) {
        super(levelModel);

        this.addKeyListener(new LevelEditorKeyController(levelModel, levelEditorView));
    }
}
