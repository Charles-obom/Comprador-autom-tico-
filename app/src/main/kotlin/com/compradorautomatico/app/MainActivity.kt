package com.compradorautomatico.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class Offer(
    val supplier: String,
    val price: Double,
    val freight: Double,
    val days: Int,
    val distance: Int,
    val quality: Double
) {
    val total: Double get() = price + freight
}

class MainActivity : AppCompatActivity() {

    private val blue = Color.rgb(13, 71, 161)
    private val yellow = Color.rgb(255, 193, 7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun base(): LinearLayout {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(28, 24, 28, 24)
        root.setBackgroundColor(Color.WHITE)
        return root
    }

    private fun title(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 25f
            setTextColor(blue)
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 10, 0, 18)
        }
    }

    private fun button(text: String): Button {
        return Button(this).apply {
            this.text = text
            setTextColor(Color.WHITE)
            setBackgroundColor(blue)
            isAllCaps = false
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 10, 0, 10) }
        }
    }

    private fun showHome() {
        val root = base()
        root.addView(title("COMPRADOR AUTOMÁTICO"))

        val subtitle = TextView(this).apply {
            text = "Encontre melhores preços e fornecedores."
            textSize = 17f
            setTextColor(Color.DKGRAY)
            setPadding(0, 0, 0, 16)
        }
        root.addView(subtitle)

        val search = EditText(this).apply {
            hint = "O que você precisa comprar?"
            textSize = 16f
            setSingleLine(true)
        }
        root.addView(search)

        val searchBtn = button("🔍  BUSCAR")
        root.addView(searchBtn)

        val cat = TextView(this).apply {
            text = "\nCategorias\n\n🚛 Pneus para caminhões\n⚡ Materiais elétricos\n🔧 Peças para empilhadeiras\n📦 Outros produtos"
            textSize = 17f
            setTextColor(Color.DKGRAY)
        }
        root.addView(cat)

        searchBtn.setOnClickListener {
            val product = search.text.toString().ifBlank { "Pneu 295/80 R22.5" }
            showComparison(product)
        }

        setContentView(root)
    }

    private fun showComparison(product: String) {
        val offers = listOf(
            Offer("Fornecedor A", 1890.0, 120.0, 3, 42, 9.2),
            Offer("Fornecedor B", 1820.0, 250.0, 5, 75, 9.0),
            Offer("Fornecedor C", 1720.0, 180.0, 4, 110, 8.9)
        )
        val best = offers.minBy { it.total + (it.days * 10) + (it.distance * 0.5) - (it.quality * 20) }

        val root = base()
        root.addView(title("Comparação de fornecedores"))

        root.addView(TextView(this).apply {
            text = "Produto: $product"
            textSize = 19f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(Color.DKGRAY)
            setPadding(0, 0, 0, 12)
        })

        root.addView(TextView(this).apply {
            text = "🏆 MELHOR CUSTO-BENEFÍCIO\n${best.supplier}"
            textSize = 19f
            setTextColor(blue)
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 10, 0, 12)
        })

        offers.forEach { offer ->
            val card = TextView(this).apply {
                text = "${offer.supplier}\n💰 Produto: R$ ${"%,.2f".format(offer.price)}\n🚚 Frete: R$ ${"%,.2f".format(offer.freight)}\n💵 Total: R$ ${"%,.2f".format(offer.total)}\n📅 ${offer.days} dias   📍 ${offer.distance} km   ⭐ ${offer.quality}/10"
                textSize = 16f
                setTextColor(Color.DKGRAY)
                setPadding(18, 18, 18, 18)
                setBackgroundColor(if (offer == best) Color.rgb(255, 248, 220) else Color.rgb(245, 245, 245))
            }
            root.addView(card)
            val contact = button("📲  Negociar com ${offer.supplier}")
            root.addView(contact)
            contact.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/5511999999999"))
                startActivity(intent)
            }
        }

        val equivalent = TextView(this).apply {
            text = "\n💡 ALTERNATIVA EQUIVALENTE\nProduto similar: R$ 1.650,00\nEconomia potencial: R$ 70,00"
            textSize = 17f
            setTextColor(blue)
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 18, 0, 12)
        }
        root.addView(equivalent)

        val back = button("← Nova busca")
        root.addView(back)
        back.setOnClickListener { showHome() }

        setContentView(root)
    }
}
