function plotter(f,n,AB,label)
    X=AB(1):0.001:AB(2);
    FX=f(X);
    SNX=sn(f,n,AB,X);
    plot(X,FX)
    hold all
    plot(X,SNX,'.')
    legend(label)
    