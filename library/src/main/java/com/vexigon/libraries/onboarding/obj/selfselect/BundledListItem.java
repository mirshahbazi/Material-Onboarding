package com.vexigon.libraries.onboarding.obj.selfselect;

/*
 * Copyright 2017 Vexigon, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;

public class BundledListItem implements Serializable {
    private String itemName, itemDesc, toggledItemDesc;
    private int drawableRes;

    public BundledListItem(String itemName, String itemDesc, int drawableRes) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.drawableRes = drawableRes;
    }

    public BundledListItem(String itemName, String itemDesc, String toggledItemDesc, int drawableRes) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.toggledItemDesc = toggledItemDesc;
        this.drawableRes = drawableRes;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getToggledItemDesc() {
        return toggledItemDesc;
    }

    public void setToggledItemDesc(String toggledItemDesc) {
        this.toggledItemDesc = toggledItemDesc;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }
}
