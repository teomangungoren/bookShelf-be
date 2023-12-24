package com.microLib.library.domain.model

import jakarta.persistence.Id
import java.util.UUID

class Post (
    @Id val id: String? = null,
     val imgId:UUID? = null,
     val description:String?,
    val ownerUsername:String?,
    )
