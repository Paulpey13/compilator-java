/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ATantQueInstTantQue extends PInstTantQue
{
    private TTantQue _tantQue_;
    private PExp _exp_;
    private TFaire _faire_;
    private PInstBloc _instBloc_;

    public ATantQueInstTantQue()
    {
        // Constructor
    }

    public ATantQueInstTantQue(
        @SuppressWarnings("hiding") TTantQue _tantQue_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TFaire _faire_,
        @SuppressWarnings("hiding") PInstBloc _instBloc_)
    {
        // Constructor
        setTantQue(_tantQue_);

        setExp(_exp_);

        setFaire(_faire_);

        setInstBloc(_instBloc_);

    }

    @Override
    public Object clone()
    {
        return new ATantQueInstTantQue(
            cloneNode(this._tantQue_),
            cloneNode(this._exp_),
            cloneNode(this._faire_),
            cloneNode(this._instBloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATantQueInstTantQue(this);
    }

    public TTantQue getTantQue()
    {
        return this._tantQue_;
    }

    public void setTantQue(TTantQue node)
    {
        if(this._tantQue_ != null)
        {
            this._tantQue_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tantQue_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TFaire getFaire()
    {
        return this._faire_;
    }

    public void setFaire(TFaire node)
    {
        if(this._faire_ != null)
        {
            this._faire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._faire_ = node;
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
            + toString(this._tantQue_)
            + toString(this._exp_)
            + toString(this._faire_)
            + toString(this._instBloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._tantQue_ == child)
        {
            this._tantQue_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._faire_ == child)
        {
            this._faire_ = null;
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
        if(this._tantQue_ == oldChild)
        {
            setTantQue((TTantQue) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._faire_ == oldChild)
        {
            setFaire((TFaire) newChild);
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
