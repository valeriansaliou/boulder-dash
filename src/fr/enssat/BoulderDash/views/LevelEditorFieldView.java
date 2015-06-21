package fr.enssat.boulderdash.views;

import fr.enssat.BoulderDash.views.FieldView;
import fr.enssat.BoulderDash.models.LevelModel;


/**
 * LevelEditorFieldView
 *
 * Game field view for the level editor.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-21
 */
public class LevelEditorFieldView extends FieldView {
    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorFieldView(LevelModel levelModel) {
        super(levelModel);
    }
}
