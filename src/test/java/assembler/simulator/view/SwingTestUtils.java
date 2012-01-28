package assembler.simulator.view;

import java.awt.Component;
import java.awt.Container;

public class SwingTestUtils
{
    public static <T extends Component> T getChildNamed(Component parent,
        String name, Class<T> clazzOfComponent)
    {
        Component obj = null;
        if (name.equals(parent.getName()))
        {
            obj = parent;
        }
        else if (parent instanceof Container)
        {
            Component[] children = ((Container) parent).getComponents();

            for (int i = 0; i < children.length; ++i)
            {
                try
                {
                    T child = getChildNamed(children[i], name,
                        clazzOfComponent);
                    obj = child;
                }
                catch (IllegalArgumentException iae)
                {
                    // This will just allow the recursive searching
                }
            }
        }

        if (obj == null)
        {
            throw new IllegalArgumentException(
                "Unable to find component by name of [" + name + "]");
        }

        return clazzOfComponent.cast(obj);
    }
}
