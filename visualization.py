import csv
from collections import defaultdict
import matplotlib.pyplot as plt

data = defaultdict(lambda: {"size": [], "operations_per_second": []})

with open("benchmark_results.csv", newline="", encoding="utf-8") as file:
    reader = csv.DictReader(file)
    for row in reader:
        variant = row["variant"]
        size = int(row["size"])
        operations_per_second = float(row["operations_per_second"])
        data[variant]["size"].append(size)
        data[variant]["operations_per_second"].append(operations_per_second)

plt.figure(figsize=(10, 6))
for variant, vals in data.items():
    plt.plot(vals["size"], vals["operations_per_second"], marker="o", label=variant)

plt.xscale("log")
plt.xlabel("Database size (number of students)")
plt.ylabel("Operations per second")
plt.title("DB strategies: operations/sec vs DB size")
plt.legend()
plt.grid(True, which="both", ls="-", lw=0.5)
plt.savefig("benchmark_result.png", dpi=150)
plt.show()

data = defaultdict(lambda: {"size": [], "operations_per_second": []})

with open("sorting_results.csv", newline="", encoding="utf-8") as file:
    reader = csv.DictReader(file)
    for row in reader:
        sorter = row["sorter"]
        size = int(row["size"])
        operations_per_second = float(row["operations_per_second"])
        data[sorter]["size"].append(size)
        data[sorter]["operations_per_second"].append(operations_per_second)

plt.figure(figsize=(10, 6))
for sorter, vals in data.items():
    plt.plot(vals["size"], vals["operations_per_second"], marker="o", label=sorter)

plt.xscale("log")
plt.xlabel("Database size (number of students)")
plt.ylabel("Operations per second")
plt.title("DB strategies: operations/sec vs DB size")
plt.legend()
plt.grid(True, which="both", ls="-", lw=0.5)
plt.savefig("sorting_result.png", dpi=150)
plt.show()

