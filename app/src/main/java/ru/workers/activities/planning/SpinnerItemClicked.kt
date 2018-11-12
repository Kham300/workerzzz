package ru.workers.activities.planning

import ru.workers.model.objects.generated.Data

interface SpinnerItemClicked {

    fun onItemClick(data: Data)
}