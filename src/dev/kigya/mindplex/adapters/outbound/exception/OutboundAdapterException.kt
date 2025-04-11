package dev.kigya.mindplex.adapters.outbound.exception

sealed interface OutboundAdapterException {
    class CountryCodeNotFoundException(message: String) :
        RuntimeException(message), OutboundAdapterException
}
