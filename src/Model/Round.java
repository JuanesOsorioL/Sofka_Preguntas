package Model;

import java.util.ArrayList;

public class Round {

    private ArrayList<Category> lstCategory;

    public Round() {

    }

    public Round(ArrayList<Category> lstCategory) {
        this.lstCategory = lstCategory;
    }

    public ArrayList<Category> getLstCategory() {
        return lstCategory;
    }

    public void setLstCategory(ArrayList<Category> lstCategory) {
        this.lstCategory = lstCategory;
    }

    public Category SelecCategory(int nivel) {
        int position = 0;
        for (int j = 0; j < lstCategory.size(); j++) {
            if (lstCategory.get(j).getLevel() == nivel) {
                position = j;
                j = lstCategory.size();
            }
        }
        return lstCategory.get(position);
    }

}
