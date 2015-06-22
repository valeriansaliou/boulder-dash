package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.exceptions.UnknownModelException;

import fr.enssat.BoulderDash.models.ExpandingWallModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.BrickWallModel;
import fr.enssat.BoulderDash.models.BoulderModel;
import fr.enssat.BoulderDash.models.DiamondModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.MagicWallModel;
import fr.enssat.BoulderDash.models.SteelWallModel;


/**
 * ModelConvertHelper
 *
 * Provides model conversion services.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-22
 */
public class ModelConvertHelper {
    /**
     * Class constructor
     */
    public ModelConvertHelper() {
        // Nothing done.
    }

    /**
     * Gets the model associated to the string
     *
     * @param   spriteName  Sprite name
     * @return  Model associated to given sprite name
     */
    public DisplayableElementModel toModel(String spriteName, boolean isConvertible) throws UnknownModelException {
        DisplayableElementModel element;

        // Instanciates the sprite element matching the given sprite name
        switch (spriteName) {
            case "black":
                element = new EmptyModel();
                break;

            case "boulder":
                element = new BoulderModel(isConvertible);
                break;

            case "brickwall":
                element = new BrickWallModel();
                break;

            case "diamond":
                element = new DiamondModel();
                break;

            case "dirt":
                element = new DirtModel();
                break;

            case "magicwall":
                element = new MagicWallModel();
                break;

            case "rockford":
                element = new RockfordModel();
                break;

            case "steelwall":
                element = new SteelWallModel();
                break;

            case "expandingwall":
                element = new ExpandingWallModel();
                break;

            default:
                throw new UnknownModelException("Unknown model element");
        }

        return element;
    }

    /**
     * Gets the string associated to the model
     *
     * @return  Model string name
     */
    public String toString(DisplayableElementModel model) {
        return model.getSpriteName();
    }
}
