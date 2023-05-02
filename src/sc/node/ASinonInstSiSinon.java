/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ASinonInstSiSinon extends PInstSiSinon
{
    private TSinon _sinon_;
    private PInstBloc _instBloc_;

    public ASinonInstSiSinon()
    {
        // Constructor
    }

    public ASinonInstSiSinon(
        @SuppressWarnings("hiding") TSinon _sinon_,
        @SuppressWarnings("hiding") PInstBloc _instBloc_)
    {
        // Constructor
        setSinon(_sinon_);

        setInstBloc(_instBloc_);

    }

    @Override
    public Object clone()
    {
        return new ASinonInstSiSinon(
            cloneNode(this._sinon_),
            cloneNode(this._instBloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASinonInstSiSinon(this);
    }

    public TSinon getSinon()
    {
        return this._sinon_;
    }

    public void setSinon(TSinon node)
    {
        if(this._sinon_ != null)
        {
            this._sinon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sinon_ = node;
    }

    public PInstBloc getInstBloc()
    {
        return this._instBloc_;
    }

    public void setInstBloc(PInstBloc node)
    {
        if(this._instBloc_ != null)
        {
            this._instBloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instBloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sinon_)
            + toString(this._instBloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sinon_ == child)
        {
            this._sinon_ = null;
            return;
        }

        if(this._instBloc_ == child)
        {
            this._instBloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._sinon_ == oldChild)
        {
            setSinon((TSinon) newChild);
            return;
        }

        if(this._instBloc_ == oldChild)
        {
            setInstBloc((PInstBloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}