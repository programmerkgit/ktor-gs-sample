package routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Customer
import models.customerStorage

fun Route.customerRouting() {
    route("/customer") {
        get {
            customerStorage.add(Customer(id = "1", firstName = "first", lastName = "last", email = "test@example.com"))
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {}
        post {}
        delete("{id}") {}
    }
}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}