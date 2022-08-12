package com.liteweb.validation.Groups;

import javax.validation.groups.Default;

public interface NormalGroups extends Default {

    interface Crud extends NormalGroups {

        interface Update extends Crud {
        }

        interface Insert extends Crud {
        }

        interface Delete extends Crud {
        }

        interface Query extends Crud {

        }
    }


}
