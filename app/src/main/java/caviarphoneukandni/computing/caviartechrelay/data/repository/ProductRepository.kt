package caviarphoneukandni.computing.caviartechrelay.data.repository

import caviarphoneukandni.computing.caviartechrelay.data.model.Product
import caviarphoneukandni.computing.caviartechrelay.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        product(
            1, "Apex Pro 14 Laptop",
            "A lightweight performance laptop with a vivid 14-inch display, 16 GB memory and all-day battery life for work and study.",
            ProductCategory.COMPUTERS, 899.00, "photo-1496181133206-80ce9b88a853",
        ),
        product(
            2, "Forge Gaming Desktop",
            "A balanced gaming tower with modern multi-core processing, dedicated graphics and generous airflow for smooth 1440p play.",
            ProductCategory.COMPUTERS, 1299.00, "photo-1593640408182-31c70c8268f5",
        ),
        product(
            3, "Arc 27 QHD Monitor",
            "A crisp 27-inch QHD IPS display with a fast refresh rate, accurate colour and an adjustable ergonomic stand.",
            ProductCategory.PERIPHERALS, 329.00, "photo-1527443224154-c4a3942d3acf",
        ),
        product(
            4, "Pulse Mechanical Keyboard",
            "Tactile hot-swappable switches, durable keycaps and customisable backlighting in a compact desk-friendly layout.",
            ProductCategory.PERIPHERALS, 109.00, "photo-1587829741301-dc798b83add3",
        ),
        product(
            5, "Vector Wireless Mouse",
            "A precise lightweight wireless mouse with programmable controls and a comfortable shape for long work sessions.",
            ProductCategory.PERIPHERALS, 69.00, "photo-1527814050087-3793815479db",
        ),
        product(
            6, "Nova GeForce Graphics Card",
            "A powerful graphics upgrade built for high-frame-rate gaming, creative workloads and efficient ray tracing.",
            ProductCategory.COMPONENTS, 649.00, "photo-1591488320449-011701bb6704",
        ),
        product(
            7, "Core 2 TB NVMe SSD",
            "High-speed PCIe storage for fast boots, responsive applications and plenty of room for games and media.",
            ProductCategory.COMPONENTS, 139.00, "photo-1597872200969-2b65d56bd16b",
        ),
        product(
            8, "Studio USB-C Hub",
            "A compact aluminium hub adding HDMI, USB, card reader and power delivery to modern laptops and tablets.",
            ProductCategory.PERIPHERALS, 59.00, "photo-1625842268584-8f3296236761",
        ),
        product(
            9, "SecureShield Premium",
            "A one-year digital licence for real-time malware defence, privacy monitoring and safe browsing across five devices.",
            ProductCategory.SOFTWARE, 49.00, "photo-1563013544-824ae1b704d3",
        ),
        product(
            10, "OfficeFlow Professional",
            "A perpetual digital productivity licence with document, spreadsheet and presentation tools for one computer.",
            ProductCategory.SOFTWARE, 179.00, "photo-1497215728101-856f4ea42174",
        ),
        product(
            11, "Echo Wireless Headset",
            "Clear spatial sound, a detachable microphone and soft memory-foam cushions for gaming and video calls.",
            ProductCategory.PERIPHERALS, 119.00, "photo-1599669454699-248893623440",
        ),
        product(
            12, "Titan 32 GB Memory Kit",
            "A matched DDR5 memory kit delivering responsive multitasking and reliable high-speed performance.",
            ProductCategory.COMPONENTS, 124.00, "photo-1562976540-1502c2145186",
        ),
    )

    private fun product(
        id: Int,
        title: String,
        description: String,
        category: ProductCategory,
        price: Double,
        imageId: String,
    ): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            category = category,
            price = price,
            imageUrl = "https://images.unsplash.com/$imageId?w=1200",
        )
    }

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
