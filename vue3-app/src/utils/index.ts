export function formatError(errors: Record<string, string[]>): string[] {
  const result: string[] = []

  for (const key in errors) {
    errors[key].forEach((element: string) => {
      result.push(`${key}:${element}`)
    })
  }

  return result
}

export function formatDate(date: string): string {
  return new Date(date).toLocaleDateString('pt-BR', {
    month: 'long',
    day: 'numeric',
    year: 'numeric',
  })
}

// Create our number formatter.
export function formatterMoney(value:string): string {
  const options = { style: 'currency', currency: 'BRL', minimumFractionDigits: 2, maximumFractionDigits: 3 }
  const formatNumber = new Intl.NumberFormat('pt-BR', options)


return formatNumber.format(Number(value))
}