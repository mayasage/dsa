package main

func time_on_space_on(nums []int) bool {
	count := make(map[int]bool, len(nums))

	for _, num := range nums {
		if count[num] {
			return true
		}
		count[num] = true
	}

	return false
}

func main() {
	nums := []int{1, 2, 3, 1}
	response := time_on_space_on(nums)
	println(response)
}