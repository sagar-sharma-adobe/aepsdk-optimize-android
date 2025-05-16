package com.adobe.marketing.optimizeapp.odd

object ODDRequestBuilder {

    fun buildRequestEvent(
        decisionScopeNames: List<String>,
        data: Map<String, Any> = emptyMap(),
        ecid: String,
    ): Map<String, Any> {

        val event = mutableMapOf<String, Any>()
        event[OddConstants.TYPE] = OddConstants.REQUEST_TYPE_ODD_FETCH

        val personalization = mutableMapOf<String, Any>()

        if(decisionScopeNames.isNotEmpty())
            personalization.put(OddConstants.OPTIMIZE_DECISION_SCOPE, decisionScopeNames)

        personalization.put(OddConstants.SEND_DISPLAY_EVENT, false)

        event[OddConstants.PERSONALIZATION] = personalization

        if(data.isNotEmpty())
            event[OddConstants.DATA] = data

        event[OddConstants.XDM] = mapOf<String, Any>(
            OddConstants.IDENTITY_MAP to getIdentityMap(ecid),
        )

        return event
    }

    private fun getIdentityMap(ecid: String) : Map<String, List<Any>> {
        val map = mutableMapOf<String, List<Any>>()
        map["ECID"] = listOf(mapOf("id" to ecid))
        return map
    }

}