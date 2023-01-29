package io.github.mimoguz.layeredfonticon.demo

import io.github.mimoguz.layeredfonticon.{Layer, LayeredFontIcon}

import java.awt.{Color, Font, GridLayout}
import javax.swing.{JButton, JFrame, JPanel, JToggleButton, SwingUtilities, UIManager, WindowConstants}
import scala.util.Try

@main def main(): Unit = SwingUtilities.invokeLater { () =>
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  Frame.setVisible(true)
}

object Frame extends JFrame():
  private val font = Font.createFont(Font.TRUETYPE_FONT, getClass.getResourceAsStream("/Layers.ttf")).deriveFont(64f)

  private val contents =
    new JPanel(GridLayout(2, 2, 4, 4)):
      add(makeButton(Symbol.Border))
      add(
        makeButton(
          Layer(Symbol.Border.text),
          Layer(Symbol.Top.text, Option(Color.BLUE)),
          Layer(Symbol.Bottom.text, Option(Color.RED)),
        ),
      )
      add(
        makeToggleButton(
          Layer(Symbol.Border.text),
          Layer(Symbol.Top.text, Option(Color.BLUE), xOffset = -2, yOffset = 4),
          Layer(Symbol.Bottom.text, Option(Color.RED), xOffset = 2, yOffset = -4),
        ),
      )
      add({
        val toggle = makeToggleButton(
          Layer(Symbol.Border.text),
          Layer(Symbol.Top.text, Option(Color.BLUE)),
          Layer(Symbol.Bottom.text, Option(Color.RED), forceColor = true),
        )
        toggle.setForeground(Color.MAGENTA)
        toggle
      })
    end new
  end contents

  setContentPane(contents)
  pack()
  setSize(300, 200)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  private def makeButton(symbol: Symbol): JButton = JButton(LayeredFontIcon(font, symbol.text))
  private def makeButton(layers: Layer*): JButton = JButton(LayeredFontIcon(font, layers*))
  private def makeToggleButton(layers: Layer*): JToggleButton = JToggleButton(LayeredFontIcon(font, layers*))
end Frame

enum Symbol(val text: String):
  case Border extends Symbol("\ue900")
  case Bottom extends Symbol("\ue902")
  case Top extends Symbol("\ue901")
