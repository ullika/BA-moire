function y=sn(f,n,AB,X)
    h=(AB(2)-AB(1))/n;
    
    function y1=a(x)
        xk=AB(1);
        while (xk+h<=x)&&(xk+h<AB(2))
            xk=xk+h;
        end
        y1=f((xk+(xk+h))/2);
        
    end 

    y=arrayfun(@a,X);

end
   