/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AEpsilonListDecVariable2 extends PListDecVariable2
{

    public AEpsilonListDecVariable2()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AEpsilonListDecVariable2();
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEpsilonListDecVariable2(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
