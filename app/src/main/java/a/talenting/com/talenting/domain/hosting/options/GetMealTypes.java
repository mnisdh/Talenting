package a.talenting.com.talenting.domain.hosting.options;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by daeho on 2017. 12. 17..
 */

public class GetMealTypes {
    @Expose private String code;
    @Expose private String msg;
    @Expose private List<MealTypes> meal_types;

    public boolean isSuccess(){
        return code.substring(0, 1).equals("2");
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public List<MealTypes> getMeal_types ()
    {
        return meal_types;
    }

    public void setMeal_types (List<MealTypes> meal_types)
    {
        this.meal_types = meal_types;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", msg = "+msg+", meal_types = "+meal_types+"]";
    }
}
